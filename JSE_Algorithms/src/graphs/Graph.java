package graphs;

import java.util.ArrayList;

public class Graph {

	private Vertex vertexs[];

	public Graph(int MAX_VERTEX) {
		vertexs = new Vertex[MAX_VERTEX + 1];
	}

	public static class Vertex implements Comparable<Vertex> {
		public int key, weight;
		public ArrayList<Vertex> adjacency;

		public double minLength = Double.POSITIVE_INFINITY;
		public Vertex previous;

		public Vertex(int key, int weigth) {
			this.key = key;
			this.weight = weigth;
			adjacency = new ArrayList<Graph.Vertex>();
		}

		public String toString() {
			return "[key=" + key + " value=" + weight + "]";
		}

		public int compareTo(Vertex other) {
			return Double.compare(minLength, other.minLength);
		}

		public void addRelation(Vertex target) {
			this.adjacency.add(target);
		}
	}

	public void addEdge(Vertex source, Vertex target) throws Exception {
		vertexs[source.key].addRelation(vertexs[target.key]);
	}

	public void addVertex(int key, int value) {
		vertexs[key] = new Vertex(key, value);
	}

	public Vertex[] getVertexs() {
		return vertexs;
	}

}
