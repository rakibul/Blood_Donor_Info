

package blooddonorinfo;

/**
 *
 * @author Mohammad Rakibul Haider
 */
public class Error_Handling {
    public boolean checkNum(String as){
        if(as.length()== 0 ) {
            return false;
        }

        for(int i=0; i<as.length(); i++){
            if(as.charAt(i)>='0' && as.charAt(i)<='9') continue;
            else return false;
        }
        return true;
    }

    public boolean checkEmail(String as){
        if(as.length()== 0 ) {
            return false;
        }

        int flag = 0;
        for(int i=0; i<as.length(); i++){
            if(flag==0){
                if(as.charAt(i)== '@') {
                    flag = 1;
                }
            }
            else{
                if(as.charAt(i)== '.') {
                    flag = 2;
                }
            }
        }
        if(flag == 2) return true;
        return false;
    }

     public boolean checkName(String as){
        if(as.length()== 0 ) {
           return false;
        }

        for(int i=0; i<as.length(); i++){
            if((as.charAt(i)>='a' && as.charAt(i)<='z') || (as.charAt(i)>='A' && as.charAt(i)<='Z') || as.charAt(i) == ' ' || as.charAt(i) == ' ') continue;
            else return false;
        }
        return true;
    }
}
