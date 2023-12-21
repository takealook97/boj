import java.util.*;

class Solution {
	static ArrayList<String> airports = new ArrayList<>();
	static ArrayList<Ticket> tickets = new ArrayList<>();
	static ArrayList<Integer>[] listArr;
	static boolean[] visit;
	static List<Integer> temp = new ArrayList<>();
	static List<Integer> answer = new ArrayList<>();
	static boolean flag = false;

	static class Ticket implements Comparable<Ticket> {
		int from, to;

		public Ticket(int from, int to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public int compareTo(Ticket o) {
			if (this.from != o.from) {
				return Integer.compare(this.from, o.from);
			} else {
				return Integer.compare(this.to, o.to);
			}
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;

			Ticket ticket = (Ticket)o;

			if (from != ticket.from)
				return false;
			return to == ticket.to;
		}

		@Override
		public int hashCode() {
			int result = from;
			result = 31 * result + to;
			return result;
		}
	}

	public String[] solution(String[][] arr) {
		// set airports
		for (String[] ticket : arr) {
			String from = ticket[0];
			String to = ticket[1];
			if (!airports.contains(from))
				airports.add(from);
			if (!airports.contains(to))
				airports.add(to);
		}
		Collections.sort(airports);

		// set tickets
		listArr = new ArrayList[airports.size()];
		for (int i = 0; i < listArr.length; i++) {
			listArr[i] = new ArrayList<>();
		}
		for (String[] ticket : arr) {
			int from = airports.indexOf(ticket[0]);
			int to = airports.indexOf(ticket[1]);
			listArr[from].add(to);
			tickets.add(new Ticket(from, to));
		}
		Collections.sort(tickets);

		visit = new boolean[tickets.size()];

		int icnIdx = airports.indexOf("ICN");
		// getRoute
		for (int i = 0; i < tickets.size(); i++) {
			Ticket ticket = tickets.get(i);
			if (ticket.from != icnIdx) {
				continue;
			}
			temp.add(ticket.from);
			temp.add(ticket.to);
			visit[i] = true;
			getRoute(ticket.to);
			visit[i] = false;
			temp.clear();
		}

		String[] ret = new String[answer.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = airports.get(answer.get(i));
		}
		return ret;
	}

	static void getRoute(int now) {
		if (isEnd()) {
			if (!flag) {
				answer.addAll(temp);
				flag = true;
				return;
			}

			for (int i = 0; i < temp.size(); i++) {
				if (answer.get(i) < temp.get(i)) {
					break;
				}
				if (answer.get(i) > temp.get(i)) {
					answer.clear();
					answer.addAll(temp);
					return;
				}
			}
		}

		for (int next : listArr[now]) {
			Ticket ticket = new Ticket(now, next);
			int idxCnt = Collections.frequency(tickets, ticket);
			if (idxCnt == 1) {
				int ticketIdx = tickets.indexOf(ticket);
				if (!visit[ticketIdx]) {
					visit[ticketIdx] = true;
					temp.add(next);
					getRoute(next);
					temp.remove(temp.size() - 1);
					visit[ticketIdx] = false;
				}
			} else {
				for (int i = 0; i < visit.length; i++) {
					if (tickets.get(i).equals(ticket) && !visit[i]) {
						visit[i] = true;
						temp.add(next);
						getRoute(next);
						temp.remove(temp.size() - 1);
						visit[i] = false;
					}
				}
			}
		}
	}

	static boolean isEnd() {
		for (boolean check : visit) {
			if (!check) {
				return false;
			}
		}
		return true;
	}
}
