package Backend;
public class CVList {

    public Node head;
    public Node tail;
    public static CVList listOfCVs = new CVList(); //this object represents our list of cvs. we've made this static so that it acts as a "global" object, meaning we can pass around the same list throughout our entire project

    public class Node
    {
        public Node next;
        public Node prev;
        public String name;
        public String age;
        public String[] experience, skills, education;
        public Node(String name, String[] experience,String[] skills,String[] education, String age)
        {
            this.name = name;
            this.experience = experience;
            this.skills = skills;
            this.education = education;
            this.age = age;
            this.next = this.prev = null;
        }
    }

    public void InsertNode(String name, String[] experience, String[] skills, String[] education, String age)
    {
        Node newCV = new Node(name, experience, skills, education, age);
        if (head == null) {
            head = tail = newCV;
        } else {
            tail.next = newCV;
            newCV.prev = tail;
            tail = newCV;
        }
    }
}

