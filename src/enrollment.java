public class enrollment {
    private String user_id;
    private String first_name;
    private String last_name;
    private int version;
    private String insurance_company;

    enrollment(){
        user_id = "";
        first_name = "";
        last_name = "";
        version = 0;
        insurance_company = "";
    }

    enrollment(String user_id, String first_name, String last_name, int version, String insurance_company){
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.version = version;
        this.insurance_company = insurance_company;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setVersion(int version){
        this.version = version;
    }

    public void setInsurance_company(String insurance_company){
        this.insurance_company = insurance_company;
    }

    public String getUser_id(){
        return user_id;
    }

    public String getFirst_name(){
        return  first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getVersion() {
        return version;
    }

    public String getInsurance_company() {
        return insurance_company;
    }
}
