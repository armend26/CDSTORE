package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class AccessCd {
    public static final String filename = "CD.ser";

    private ArrayList<CD> cd = new ArrayList<CD>();
    InputStream file, buffer;
    OutputStream bf, fl;
    ObjectInput input;
    ObjectOutput output;
    File uf = new File(filename);

    public AccessCd(){
        readF();
    }


    public ArrayList<CD> getCd()
    {
        this.readF();
        return this.cd;
    }


    public void addCd(CD emp) {
        cd.add(emp);
        writeF();
    }


    public void rm(int id){
        ObservableList<CD> cdList = FXCollections.observableArrayList(getCd());
        for(CD x: cdList){
            if(x.getCdId()==id){
                cdList.removeAll(x);
            }
        }
    }

    public void editCdQuant(int quant,CD c){
        c.setCdQuantity(quant);
        this.writeF();
    }



    @SuppressWarnings("unchecked")
    private void readF() {
        try {
            // use buffering
            file = new FileInputStream(uf);
            buffer = new BufferedInputStream(file);
            input = new ObjectInputStream(buffer);
            // deserialize the List
            cd = (ArrayList<CD>) input.readObject();
            // display its data
            for (CD emp : cd) {
                System.out.println("Data: " + emp.toString());
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("File Not well defined. Creating new file"
                    + ex.toString());
        } catch (IOException ex) {
            System.out.println("Cannot perform input." + ex.toString());
        }
        closeFile();
    }

    private void writeF() {
        // serialize the List
        try {
            fl = new FileOutputStream(uf);
            bf = new BufferedOutputStream(fl);
            output = new ObjectOutputStream(bf);
            output.writeObject(cd);
        } catch (IOException ex) {
            System.out.println("Cannot perform output." + ex.toString());
        }
        closeFile();
    }

    public void closeFile() {
        try {
            if (input != null) {
                input.close();
                buffer.close();
                file.close();
            }
            if (output != null) {
                output.close();
                bf.close();
                fl.close();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
