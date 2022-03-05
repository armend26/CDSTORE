package model;

import java.io.*;
import java.util.ArrayList;

public class AccessEmployee {
    public static final String filename = "Employee.ser";

    private ArrayList<Employee> empl = new ArrayList<Employee>();
    InputStream file, buffer;
    OutputStream bf, fl;
    ObjectInput input;
    ObjectOutput output;
    File uf = new File(filename);

    public AccessEmployee(){
        readF();
    }

    public ArrayList<Employee> getEmp()
    {
        this.readF();
        return this.empl;
    }


    public void editEmployee(int pos, Employee u)
    {
        System.out.println(">>>>"+pos);
        if(pos < 0 || pos >= empl.size())
        {
            System.out.println("Cannot find User");
            return;
        }
        else
        {
            empl.set(pos, u);
            this.writeF();
        }
    }

    public void addEmp(Employee emp) {
        empl.add(emp);
        writeF();
    }


    @SuppressWarnings("unchecked")
    private void readF() {
        try {
            // use buffering
            file = new FileInputStream(uf);
            buffer = new BufferedInputStream(file);
            input = new ObjectInputStream(buffer);
            // deserialize the List
            empl = (ArrayList<Employee>) input.readObject();
            // display its data
            for (Employee emp : empl) {
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
            output.writeObject(empl);
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
