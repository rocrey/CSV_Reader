import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class csv_reader {
    public static void main(String[] args){
        read("C:/Users/DAVIDREY/Documents/CSVTest.csv");
    }
    public static final String delimiter = ",";

    public static void read(String csvFile){
        ArrayList<String> ins_companies = new ArrayList<>();
        LinkedList<enrollment> enrollments = new LinkedList<>();
        enrollment temp_enroll = null;
        try{
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] splits;
            while((line = br.readLine()) != null){
                splits = line.split(delimiter);
                if (!ins_companies.contains(splits[4])){
                    ins_companies.add(splits[4]);
                }
                int version = Integer.parseInt(splits[3]);
                temp_enroll = new enrollment(splits[0], splits[1], splits[2], version, splits[4]);
                if (enrollments.size() > 0) {
                    int found_enroll = -1;
                    boolean found_ins = false;
                    boolean canAdd = true;
                    for (enrollment e : enrollments) {
                        if (e.getInsurance_company().compareTo(temp_enroll.getInsurance_company()) == 0 &&
                                e.getUser_id().compareTo(temp_enroll.getUser_id()) == 0) {
                            if (temp_enroll.getVersion() > e.getVersion()){
                                e.setUser_id(temp_enroll.getUser_id());
                                e.setFirst_name(temp_enroll.getFirst_name());
                                e.setLast_name(temp_enroll.getLast_name());
                            }
                            canAdd = false;
                            break;
                        } else {
                            if (e.getInsurance_company().compareTo(temp_enroll.getInsurance_company()) == 0) {
                                found_ins = true;
                                if (e.getLast_name().compareTo(temp_enroll.getLast_name()) > 0) {
                                    found_enroll = enrollments.indexOf(e);
                                } else if (e.getLast_name().compareTo(temp_enroll.getLast_name()) == 0 &&
                                        e.getFirst_name().compareTo(temp_enroll.getFirst_name()) >= 0) {
                                    found_enroll = enrollments.indexOf(e);
                                }
                            }
                            if (found_ins && e.getInsurance_company().compareTo(temp_enroll.getInsurance_company()) != 0) {
                                if (found_enroll < 0) found_enroll = enrollments.indexOf(e);
                                break;
                            }
                        }
                    }
                    if (canAdd) {
                        if (found_enroll >= 0) enrollments.add(found_enroll, temp_enroll);
                        else enrollments.add(temp_enroll);
                    }
                }
                else {
                    enrollments.add(temp_enroll);
                }
            }
            br.close();
            for(String ic : ins_companies){
                PrintWriter writer = null;
                writer = new PrintWriter(new File("C:/Users/DAVIDREY/Documents/" + ic + ".csv"));
                for(enrollment e : enrollments){
                    if (ic.compareTo(e.getInsurance_company()) == 0){
                        StringBuilder strBldr = new StringBuilder();
                        strBldr.append(e.getUser_id() + ",");
                        strBldr.append(e.getFirst_name() + ",");
                        strBldr.append(e.getLast_name() + ",");
                        strBldr.append(e.getVersion() + ",");
                        strBldr.append(e.getInsurance_company() + ",");
                        strBldr.append("\n");
                        writer.write(strBldr.toString());
                    }
                }
                writer.close();
            }
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
