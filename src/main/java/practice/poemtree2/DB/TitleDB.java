package practice.poemtree2.DB;

import java.util.List;

import practice.poemtree2.domain.ListInfo;

public interface TitleDB {

    /*
     * @Param : set of favor
     * 
     * return : cid
     */
    public String insertTitle(ListInfo listInfo);

    /*
     * @Param : cid
     * 
     * return : A set of content labeled with cid.
     */
    public ListInfo findTitleByCID(String cid);

    /*
     * @param : The set of cid..
     * 
     * return : list of contents matched as set of cids.
     */
    public List<ListInfo> getListInfobyCIDSet(List<String> CID);
}
