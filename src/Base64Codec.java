import java.util.Map;

/**
 * Created by UserName on 08.07.2016.
 */
public class Base64Codec {
    static char[] chArray;
    static Map<Character, Integer> chMap;

    static public void Init() {
        Init("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz./");
    }

    static public void Init(String s) {
        chArray = s.toCharArray();
        for(int i=0; i<chArray.length; i++){
            chMap.put(chArray[i], i);
        }
    }

    static public char EncodeToChar(int n) {
        return (n & ~63) == 0 ? chArray[n] : '?';
    }

    static public String Encode(int n) {
        String s = "=";
        while (n != 0) {
            s = EncodeToChar(n & 63) + s;
            n >>= 6;
        }
        return s;
    }

    static public int Decode(char c) {
        return chMap.containsKey(c) ? chMap.get(c) : 0;
    }
}
