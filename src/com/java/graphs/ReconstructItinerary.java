package com.java.graphs;
//https://leetcode.com/problems/reconstruct-itinerary/description/
import java.util.*;

public class ReconstructItinerary {

    // each time the values has to be maintained in a lexicographical order, that's y we use Priority queue
    Map<String, PriorityQueue<String>> flights;
    LinkedList<String> path;

    public List<String> findItinerary(String[][] tickets) {
        flights = new HashMap<>();
        path = new LinkedList<>();
        for (String[] ticket : tickets) {
            flights.putIfAbsent(ticket[0], new PriorityQueue<>());
            flights.get(ticket[0]).add(ticket[1]);
        }
        dfs("JFK");
        return path;
    }

    public void dfs(String departure) {
        PriorityQueue<String> arrivals = flights.get(departure);
        while (arrivals != null && !arrivals.isEmpty())
            dfs(arrivals.poll());
        path.addFirst(departure);
    }

    public static void main(String[] args) {
        ReconstructItinerary reconstructItinerary = new ReconstructItinerary();
//        System.out.println(reconstructItinerary.findItinerary(new String[][]{{"MUC","LHR"}, {"JFK","MUC"}, {"SFO","SJC"}, {"LHR","SFO"} }));
        System.out.println(reconstructItinerary.findItinerary(new String[][]{{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}}));
    }
}
