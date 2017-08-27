package pong;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Status;
import akka.japi.pf.ReceiveBuilder;
import scala.PartialFunction;

public class JavaPongActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("Ping", s ->
                        sender().tell("Pong", ActorRef.noSender()))
                .matchAny(x ->
                        sender().tell(
                             new Status.Failure(new Exception("unknown message")), self()
                         ))
                .build();
    }
}
