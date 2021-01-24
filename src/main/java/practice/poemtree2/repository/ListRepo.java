package practice.poemtree2.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListRepo {

    List<String> CIDs;
    Random rand;

    public void setRepo(List<String> CIDs) {
        this.CIDs = CIDs;
    }

    public List<String> getRepo() {
        return CIDs;
    }

    public List<String> getRepoPart() {

        rand = new Random();
        int listNum = 10;
        int bound = this.CIDs.size();
        List<String> newCIDs = new ArrayList<String>();
        for (int i = 0; i < listNum; i++) {
            newCIDs.add(this.CIDs.get(rand.nextInt(bound)));
        }

        return newCIDs;
    }

}
