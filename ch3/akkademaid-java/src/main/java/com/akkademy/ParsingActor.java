package com.akkademy;


import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import scala.PartialFunction;

public class ParsingActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ParseHtmlArticle.class, msg -> {
                    String body = ArticleExtractor.INSTANCE.getText(msg.htmlString);
                    sender().tell(new ArticleBody(msg.uri, body), self());
                }).build();
    }
}
