package practice.poemtree2.DB;

public interface BodyDB {

    /*
     * @Param : cid, body
     * 
     * return cid
     */
    public String insertBody(String cid, String body);

    /*
     * @Param : cid
     * 
     * return : body
     */
    public String findBodyByCID(String cid);

}
