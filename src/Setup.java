
public abstract class Setup {
	public class Node {

		private String city;
		private int vertex;
		private Node next;

		public Node(String city, int vertex, Node next) {
			this.city = city;
			this.vertex = vertex;
			this.next = next;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public void setVertex(int vertex) {
			this.vertex = vertex;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public String getCity() {
			return city;
		}

		public int getVertex() {
			return vertex;
		}

		public Node getNext() {
			return next;
		}
	}

	public class Edge {

		private String city;
		private int neighbor;
		private int cost;
		private Edge next;

		public Edge(String city, int neighbor, int cost, Edge next) {
			this.city = city;
			this.neighbor = neighbor;
			this.cost = cost;
			this.next = next;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public void setNeighbor(int neighbor) {
			this.neighbor = neighbor;
		}

		public void setCost(int cost) {
			this.cost = cost;
		}

		public void setNext(Edge next) {
			this.next = next;
		}

		public String getCity() {
			return city;
		}

		public int getNeighbor() {
			return neighbor;
		}

		public int getCost() {
			return cost;
		}

		public Edge getNext() {
			return next;
		}
	}

	public class Path {

		private String city;
		private Path next;

		public Path(String city, Path next) {
			this.city = city;
			this.next = next;
		}

		public void setCity(String city) {
			this.city = city;
		}
		public void setNext(Path next) {
			this.next = next;
		}

		public String getCity() {
			return city;
		}

		public Path getNext() {
			return next;
		}
	}

	public class Vertex {

		private int distance;
		private int path;
		private boolean known;

		public Vertex(int distance, int path, boolean known) {
			this.distance = distance;
			this.path     = path;
			this.known    = known;
		}

		public void setDistance(int distance) {
			this.distance = distance;
		}

		public void setPath(int path) {
			this.path = path;
		}

		public void setKnown(boolean known) {
			this.known = known;
		}

		public int getDistance() {
			return distance;
		}

		public int getPath() {
			return path;
		}

		public boolean getKnown() {
			return known;
		}
	}
}
