package practice.poemtree2.DB;

import java.util.List;
import java.util.Map;

import practice.poemtree2.domain.FavorInfo;

public interface FavorDB {

    /*
     * @Param : set of favor
     * 
     * return : cid
     */
    public String insertFavor(String cid, FavorInfo favorInfo);

    /*
     * @Param : cid
     * 
     * return : A set of favor labeled with cid.
     */
    public FavorInfo findFavorByCID(String cid);

    /*
     * @param : no
     * 
     * return : list of all the cids
     */
    public List<String> getALLCID();

    /*
     * @param : genre / form / language
     * 
     * return : list of matched cids
     */
    public List<String> getListofCIDonFavor(Map<String, String> favorMap);

}
