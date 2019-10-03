import java.util.*;
import java.io.*;
import java.lang.*;
class StudentAlreadyExistsException extends Exception{
StudentAlreadyExistsException(String s){
System.out.println(s);
}
}
class Department{
     private String deptName;
    void adddname(String s){
        this.deptName=s;
    }
    String getdn(){
        return(this.deptName);
    }
    private String deptHead;
    void adddhead(String s){
        this.deptHead=s;
    }
    String getdh(){
        return(this.deptHead);
    }
}
class Student{
    private int studentID;
    private String studentName;
    private int creditScore;
    void addid(int id){
        this.studentID=id;
    }
    void adding(int cs,String name){
        this.studentName=name;
        this.creditScore=cs;
    }
    int geti(){
        return(this.studentID);
    }
    int getsc(){
        return(this.creditScore);
    }
    String getnam(){
        return(this.studentName);
    }
}
class studentmanagment{
    ArrayList<Student> stud = new ArrayList<Student>();
    ArrayList<Department> depmt=new ArrayList<Department>();
    ArrayList<Student> in=new ArrayList<Student>();
    boolean enrollStudent(Student s,String dep,Department dd){
        int t=0,i,j;
       if(stud.size()==0){
            System.out.println("Student is elligible for adding");
           return(true);
       }
       else{
       for(i=0;i<stud.size();i++){
            if(stud.get(i).geti()==s.geti()){
                t=1;
            }   
           }
       }
       if(t==0){
           System.out.println("Student is elligible for adding");
           return(true);
       }
       else{
           return(false);
       }
    }
        void addds(Student s,String dep,Department dd){
            stud.add(0,s);
            depmt.add(0,dd);
            System.out.println("added");
        }
        public ArrayList<Student> getStudentsByDepartment(String dep,Department dd){
            int i;
            for(i=0;i<depmt.size();i++){
                if(depmt.get(i).getdn().equals(dep)){
                    in.add(stud.get(i));
                                 }
            }
          
            
            return(in);
        }
        public void displayStudents(String dept,ArrayList<Student> outt){
            int i,j;
            if(outt.size()==0){
                System.out.println("there are no elements in this dept");
            }
            else{
                if(outt.size()==1){
                    System.out.println(outt.get(0).geti()+" "+outt.get(0).getnam()+" "+outt.get(0).getsc()+" "+dept);
                }
                else{
                    for(i=0;i<outt.size()-1;i++){
                        for(j=i+1;j<outt.size();j++){
                            if(outt.get(i).getsc()<outt.get(j).getsc()){
                                Collections.swap(outt,i,j);
                            }
                        }
                    }
                    for(i=0;i<outt.size();i++){
                        System.out.println(outt.get(i).geti()+" "+outt.get(i).getnam()+" "+outt.get(i).getsc()+" "+dept);
                    }
                }
            }
            
}
}
public class studentdatabase{
    public static void main(String args[]){
        int i,j,n;
        studentmanagment sm=new studentmanagment();
        Scanner sc=new Scanner(System.in);
        ArrayList<Student> outt = new ArrayList<Student>();
        while(true){
            System.out.println("enter choice 1=enroll\n2=display\n3=exit\n");
            int x=sc.nextInt();
            if(x==3){
                break;
            }
            switch(x){
                case 1:System.out.println("enter no. of students to enroll\n");
                n=sc.nextInt();
                for(i=0;i<n;i++)
                {
                    System.out.println("enter dept name and head.\n for exit=0\n");
                    Department d=new Department();
                    String l=sc.next();
                    String k=l.toLowerCase();
                    String g=sc.next();
                    String o=g.toLowerCase();
                    d.adddname(k);
                    d.adddhead(o);
                    if(k=="0"){
                        break;
                    }
                    System.out.println("enter student id");
                    int id=sc.nextInt();
                    Student s=new Student();
                    s.addid(id);
                    boolean r=sm.enrollStudent(s,k,d);
                    try{
                    if(r){
                        System.out.println(" \nenter name and score ofstudent\n");
                        String name=sc.next();
                        int m=sc.nextInt();
                        s.adding(m,name);
                        sm.addds(s,k,d);
                    }
                    else{
                        throw new StudentAlreadyExistsException("sorry id exists, take another chance\n");
                    }
                    }catch(StudentAlreadyExistsException e){i=i-1;}
                }break;
                case 2:System.out.println("enter dept name");
                String dep=sc.next();
                Department d=new Department();
                outt= sm.getStudentsByDepartment(dep,d);
                sm.displayStudents(dep,outt); break;
                case 3:break;
            }
            
        }
    } 
}