package practice.poemtree2.DB;

public interface StarDB {

    /*
     * @Param : cid, star
     * 
     * return cid
     */
    public String insertBody(String cid, int star);

    /*
     * @Param : cid
     * 
     * return : star
     */
    public int findstarByCID(String cid);

    /*
     * @Param : cid
     * 
     * return : updated value of star
     */
    public int updateStar(String cid, int star);

}
