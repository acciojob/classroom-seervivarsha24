package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class StudentRepository {
    private HashMap<String,Student> students;
    private HashMap<String,Teacher> teachers;
    private HashMap<String, List<String>> teacherandstudent;

    public StudentRepository() {
        this.students = new HashMap<>();
        this.teachers = new HashMap<>();
        this.teacherandstudent = new HashMap<>();
    }

    public void addStudent(Student student) {
        students.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if(students.containsKey(student) && teachers.containsKey(teacher)){
            List<String> st=new ArrayList<>();
            if(teacherandstudent.containsKey(teacher)) st=teacherandstudent.get(teacher);
            st.add(student);
            teacherandstudent.put(teacher,st);
        }
    }

    public Student getStudentByName(String name) {
        return students.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teachers.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return teacherandstudent.get(teacher);
    }

    public List<String> getAllStudents() {
        List<String> a=new ArrayList<>();
        for(String x:students.keySet())
            a.add(x);
        return a;
    }

    public void deleteTeacherByName(String teacher) {
        if(teacherandstudent.containsKey(teacher)){
            List<String> a=teacherandstudent.get(teacher);
            for(int i=0;i<a.size();i++){
                students.remove(a.get(i));
            }
            teachers.remove(teacher);
            teacherandstudent.remove(teacher);
        }
    }

    public void deleteAllTeachers() {
        for(String a:teacherandstudent.keySet()){
            deleteTeacherByName(a);
        }
    }
}
