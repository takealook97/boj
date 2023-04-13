import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;

    static HashSet<Integer> peopleKnow = new HashSet<>();
    static ArrayList<Party> parties = new ArrayList<>();

    static class Party {
        ArrayList<Integer> people;
        boolean partyKnows;

        public Party(ArrayList<Integer> people, boolean partyKnows) {
            this.people = people;
            this.partyKnows = partyKnows;
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int firstSizeOfTruth = Integer.parseInt(st.nextToken());
        for (int i = 0; i < firstSizeOfTruth; i++) {
            peopleKnow.add(Integer.parseInt(st.nextToken()));
        }
        if (firstSizeOfTruth == 0) {
            System.out.println(M);
            System.exit(0);
        }

        for (int i = 0; i < M; i++) {
            makeParty();
        }

        while (true) {
            HashSet<Integer> now = new HashSet<>(peopleKnow);
            for (int i = 0; i < parties.size(); i++) {
                Party party = parties.get(i);
                if (!party.partyKnows) {
                    parties.set(i, changeStatus(party));
                }
            }
            HashSet<Integer> after = new HashSet<>(peopleKnow);
            if (now.equals(after)) break;
        }

        int result = 0;
        for (Party party : parties) {
            if (!party.partyKnows) {
                result++;
            }
        }
        System.out.println(result);
    }

    static void makeParty() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int peopleInParty = Integer.parseInt(st.nextToken());
        ArrayList<Integer> party = new ArrayList<>();
        for (int i = 0; i < peopleInParty; i++) {
            party.add(Integer.parseInt(st.nextToken()));
        }
        parties.add(new Party(party, false));
    }

    static Party changeStatus(Party party) {
        boolean check = hasCommon(party.people);
        if (check) {
            peopleKnow.addAll(party.people);
            party.partyKnows = true;
        }
        return party;

    }

    static boolean hasCommon(ArrayList<Integer> people) {
        HashSet<Integer> temp = new HashSet<>(people);
        temp.retainAll(peopleKnow);
        return !temp.isEmpty();
    }
}
