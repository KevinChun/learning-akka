package com.akkademy;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Complete future after 2000 messages. Used for testing.
 */
public class TestCameoActor extends AbstractActor {
    private final CompletableFuture futureToComplete;
    private List<String> articles = new ArrayList<String>();

    private TestCameoActor(CompletableFuture futureToComplete) {
        this.futureToComplete = futureToComplete;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, x -> {
                            articles.add(x);
                            if (articles.size() == 2000) {
                                futureToComplete.complete("OK!");
                            }
                        }
                ).build();
    }
}
