package graphs;

import graphs.Graph.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

	public void findPaths(Vertex start) {
		start.minLength = 0;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(start);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();
			for (Vertex e : u.adjacency) {
				Vertex v = e;
				double weight = e.weight;
				double distanceThroughU = u.minLength + weight;
				if (distanceThroughU < v.minLength) {
					vertexQueue.remove(v);
					v.minLength = distanceThroughU;
					v.previous = u;
					vertexQueue.add(v);
				}
			}
		}
	}

	public List<Vertex> getShortestPathTo(Vertex target) {
		List<Vertex> path = new ArrayList<Graph.Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
			path.add(vertex);
		Collections.reverse(path);
		return path;
	}
}