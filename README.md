# learning-akka
러닝 아카(Learning Akka) 번역서의 예제 코드 레포지토리입니다. 이 레포지토리에 포함된 코드들은 원서에서 사용한 예제 코드를 현재의 최신 버전에 맞춰 변경한 코드를 소개합니다.

## 버전
이 코드들은 아래의 버전에서 동작하도록 변경 되었습니다.

* Scala version: 2.12.2
* Akka version: 2.5.4


## 아카 버전 2.5 마이그레이션
아카는 빈번하고 활발하게 버전을 변경하고 있기 때문에, 이번 버전 2.5 업그레이드에서도 많은 변화가 있었습니다.
아카의 마이그레이션에 관한 내용은 아카가 소개하고 있는 마이그레이션 가이드라인([여기](http://doc.akka.io/docs/akka/current/java/project/migration-guide-2.4.x-2.5.x.html))를 확인하기 바랍니다.

아래에서는 러닝 아카의 예제에서 사용한 코드를 아카 2.5 버전으로 마이그레이션 하면서 변경이 필요한 부분들을 공통 부분과 자바 코드로 나누어 나열합니다.

### 공통부분
아카 2.5를 적용했을 때 자바 DSL 같은 특정 부분에 한정된 게 아닌 공통적으로 적용되는 변경에 대해서 설명합니다.

##### activator 대신 sbt
아카는 더 이상 activator를 지원하지 않습니다 대신 sbt애 Giter8이라는 템플릿 기능을 추가해서 activator를 대체합니다.
사용 방법은 아주 간단합니다 예를 들어 sbt를 사용해서 java와 scala 코드를 모두 사용하는 아카 프로젝트를 생성하려면 터미널에서 아래와 같이 실행하면 됩니다.    
```
sbt new akka/hello-akka.g8
```

sbt가 공식적으로 지원하는 Giter8 템플릿은 아래와 같습니다. 물론 사용자가 정의한 템플릿도 생성할 수 있습니다.

* foundweekends/giter8.g8 (A template for Giter8 templates)
* scala/scala-seed.g8 (Seed template for Scala)
* scala/hello-world.g8 (A template to demonstrate a minimal Scala application)
* akka/akka-scala-seed.g8 (A minimal seed template for an Akka with Scala build )
* akka/akka-java-seed.g8 (A minimal seed template for an Akka in Java )
* akka/hello-akka.g8 (Simple Akka application)
* playframework/play-scala-seed.g8 (Play Scala Seed Template)
* playframework/play-java-seed.g8 (Play Java Seed template)
* lagom/lagom-scala.g8 (A Lagom Scala seed template for sbt)
* lagom/lagom-java.g8 (A Lagom Java seed template for sbt)
* scala-native/scala-native.g8 (Scala Native)
* scala-native/sbt-crossproject.g8 (sbt-crosspoject)
* http4s/http4s.g8 (http4s services)
* unfiltered/unfiltered.g8 (Unfiltered application)
* scalatra/scalatra-sbt.g8 (Basic Scalatra template using SBT 0.13.x.) 

sbt의 Giter8 템플힛에 관한 자세한 내용은 sbt의 공식 문서([여기](http://www.scala-sbt.org/0.13/docs/sbt-new-and-Templates.html))를 참고하기 바랍니다.


##### `Agent` deprecated
`Agent`는 그 용도의 모호함 때문에 더 이상 사용되지 않고 제거 되었습니다.

*그래서 `Agent`의 예제코드가 있는 ch9 폴더의 akka-agent 프로젝트에서는 원서에서 사용한 아카 버전을 그대로 사용했습니다.*


##### Cluster Client
아카 클러스터 클라이언트를 구현할 때, application.conf 파일에 명시하던 akka.actor.provider의 값이 각각 아래와 같이 변경 되었습니다.
> *akka.cluster.ClusterActorRefProvider* -> *cluster*

> *akka.remote.RemoteActorRefProvider* -> *remote*

그리고 `ClusterReceptionistExtension` 클래스는 `ClusterClientReceptionist`로 이름이 변경 되었습니다.
따라서, application.conf 파일도 아래와 같이 변경해야 합니다.
```
akka.extension = ["akka.cluster.client.ClusterClientReceptionist"]
```

##### Actor system shutdown
ActorSystem.awaitTermination은 없어지고, 대신 ActorSystem.terminate 또는 ActorSystem.whenTerminated를 사용합니다.
그리고 이 두 메서드는 액터 시스템이 종료 될 때 모두 `Future[Terminated]`를 리턴합니다. 


### Java
아카 2.5를 적용하면서 Java DSL 코드를 변경한 내용들을 나열합니다

##### AbstractActor의 createReceive 메서드
AbstractActor을 확장해서 액터를 생성할 때 구현해야 했던 receive 메서드 대신 createReceive 메서드를 구현합니다.
아래는 아카 문서에서 소개하는 AbstractActor의 샘플 코드입니다.

Old:

```
import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

public class SomeActor extends AbstractActor {
  public SomeActor() {
    receive(ReceiveBuilder
      .match(String.class, s -> System.out.println(s.toLowerCase())).
      .build());
  }
}
```

New:
```
import akka.actor.AbstractActor;

public class SomeActor extends AbstractActor {
  @Override
  public Receive createReceive() {
    return receiveBuilder()
      .match(String.class, s -> System.out.println(s.toLowerCase()))
      .build();
  }
}
```

##### `Timeout.longToTimeout` deprecated
Timeout 객체의 longToTimeout 메서드는 아카 2.4 이후로 지원되지 않습니다.


##### Become/Unbecome
4장에서 become()과 nnbecome로 액터가 전달 받은 메시지에 따라 액터의 행동을 변경할 수 있는데, 이때 코드의 구현 형태가 조금 변경 되었는데 예제에서 사용된 코드를 비교해 보면 아래와 같습니다

Old:
```
private PartialFunction<Object, BoxedUnit> disconnected;
private PartialFunction<Object, BoxedUnit> online;

public HotswapClientActor(String dbPath) {
    remoteDb = context().actorSelection(dbPath);

    disconnected = ReceiveBuilder.
            match(Request.class, x -> { //can't handle until we know remote system is responding
                remoteDb.tell(new Connected(), self()); //see if the remote actor is up
                stash(); //stash message for later
            }).
            match(Connected.class, x -> { // Okay to start processing messages.
                context().become(online);
                unstash();
            }).build();

    online = ReceiveBuilder.
                    match(Request.class, x -> {
                        remoteDb.forward(x, context()); //forward instead of tell to preserve sender
                    }).
                    build();

    receive(disconnected); //initial state.
}
``` 

New:
```
@Override
public Receive createReceive() {
    return receiveBuilder()
            .match(Request.class, x -> { //can't handle until we know remote system is responding
                remoteDb.tell(new Connected(), self()); //see if the remote actor is up
                stash(); //stash message for later
            })
            .match(Connected.class, c -> { // Okay to start processing messages.
                getContext().become(
                        receiveBuilder()
                                .match(Request.class, r -> {
                                    remoteDb.forward(r, context()); //forward instead of tell to preserve sender
                                })
                                .build()
                );
                unstash();
            })
            .build();
}
```

