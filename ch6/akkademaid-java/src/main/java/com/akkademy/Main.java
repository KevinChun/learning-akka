package com.akkademy;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.client.ClusterClientReceptionist;
import akka.routing.BalancingPool;

public class Main {
    public static void main(String... args) {
        ActorSystem system = ActorSystem.create("Akkademy");
        ActorRef clusterController = system.actorOf(Props.create(ClusterController.class), "clusterController");

        ActorRef workers = system.actorOf(new BalancingPool(5).props(Props.create(ArticleParseActor.class)), "workers");

        ((ClusterClientReceptionist) akka.cluster.client.ClusterClientReceptionist.apply(system)).
                registerService(workers);
    }
}
