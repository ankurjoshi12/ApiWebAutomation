package SignupTest;

public class UserRequestyPojo {

    String fact ;
    int length ;

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

//    public int getLength() {
//        return length;
//    }
//
//    public void setLength(int length) {
//        this.length = length;
//    }

    public UserRequestyPojo(){
    }
    public UserRequestyPojo(String fact, int length) {
        this.fact = fact;
        this.length = length;
    }

}
