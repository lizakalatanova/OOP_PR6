import java.math.BigInteger;
import java.util.*;

public class Task6 {
    public static void main(String[] args) {
        List<Integer> chisl6 = new ArrayList<Integer>();
        List<Integer> chisl7 = new ArrayList<Integer>();
        Scanner c = new Scanner(System.in);
        /////№1
        String st1 = c.nextLine();
        String st2 = c.nextLine();
        System.out.println(hiddenAnagram(st1,st2));
        /////№2
        c = new Scanner(System.in);
        String st12 = c.nextLine();
        int a2 = c.nextInt();
        System.out.println(collect(st12,a2));
        /////№3
        c = new Scanner(System.in);
        String st13 = c.nextLine();
        String st23 = c.nextLine();
        System.out.println(nicoCipher(st13,st23));
        /////№4
       int[] ar = {1, 2, -1, 4, 5, 6, 10, 7};
        System.out.println(twoProduct(ar,20)[0]+ " " +twoProduct(ar,20)[1]);
        /////№5
        c = new Scanner(System.in);
        int a5 = c.nextInt();
        System.out.println(isExact(a5,1));
        /////№6
        c = new Scanner(System.in);
      String st16 = c.nextLine();
        System.out.println(fractions(st16));
        /////№7
        c = new Scanner(System.in);
       String a7 = c.nextLine();
        System.out.println(pilish_string(a7));
        /////№8
        c = new Scanner(System.in);
        int a8 = c.nextInt();
        System.out.println(generateNonconsecutive(a8));
        /////№9
        c = new Scanner(System.in);
        String a9 = c.nextLine();
        System.out.println(isValid(a9));
        /////№10
        int[] arr = {1, 2, 3, 7, 9};
        System.out.println(sumsUp(arr));
    }
    public static boolean isAnagram(String str1, String str2){ // доп для 1 задачи, проверка на совпадение строк
        if(str1.length() != str2.length()){
            return false;
        }
        String S = str1, T = str2;
        for(int i = 0; i < str1.length(); i++){
            if(!T.contains("" + str1.charAt(i))){  // проверка на соотвествие
                return false;
            }
            T = T.substring(0,T.indexOf(str1.charAt(i))) + T.substring(T.indexOf(str1.charAt(i)) + 1);  // переопределяем Т
            S = S.substring(0,S.indexOf(str1.charAt(i))) + S.substring(S.indexOf(str1.charAt(i)) + 1);
        }
        return T.length() == 0 && S.length() == 0;  // если Т и S обнулились, значит прошло совпадение
    }
    public static String hiddenAnagram(String mainss, String notmainss){ // анаграмма второй строки в первой
        String mains = "", notmains = ""; // преобразовываем строки
        for(int i = 0; i < mainss.length(); i++){
            if(Character.isAlphabetic(mainss.charAt(i))){
                mains += mainss.charAt(i);
            }
        }
        for(int i = 0; i < notmainss.length(); i++){
            if(Character.isAlphabetic(notmainss.charAt(i))){
                notmains += notmainss.charAt(i);
            }
        }
        if(mains.length() < notmains.length()){   //Если анаграмма больше строки, то искать не имеет смысла
            return "false";
        }
        mains = mains.toLowerCase(Locale.ROOT);
        notmains = notmains.toLowerCase(Locale.ROOT);
        for(int i = 0; i <= mains.length() - notmains.length(); i++){        // выделяем из главной строки, строку с длиной второй строки
           if(isAnagram(mains.substring(i, i + notmains.length()), notmains)){
                return mains.substring(i, i + notmains.length());
            }
        }
        return "false";
    }
    public static ArrayList<String> collect(String str, int len){ // деление строки на части
        if (len >= str.length())
            return new ArrayList<String>();
        StringBuilder work = new StringBuilder(str);
        String midResult = work.substring(0, len); //Вырезаем из строки нужную длину
        ArrayList<String> result;
        result = collect(work.substring(len,str.length()), len); // повторяем действие пока длина не станет больше оставшейся строки
        result.add(midResult); //Добавляем вырезанную часть
        Collections.sort(result); //Сортируем
        return result;
    }
    public static void swapColumns(char[][] charr, int c1, int c2){ // доп для 3, смена столбцов
        char temp;
        for(int i = 0; i < charr.length; i++){
            temp = charr[i][c1];
            charr[i][c1] = charr[i][c2];
            charr[i][c2] = temp;
        }
    }
    public static String nicoCipher(String message, String key){ // шифрование сообщения по ключу
        int s2 = key.length(); // длина ключа
        int s1 = (int)Math.ceil(((double)message.length() / s2)); // количество строк

        //Формируем массив для перестановок
        char[][] arr = new char[s1][s2];

        int si = 0; //Заполняем его
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++, si++){
                arr[i][j] = si < message.length()? message.charAt(si) : ' ';
            }
        }

        char[] arrkey = key.toCharArray(); //Переодим ключ в массив char
        Arrays.sort(arrkey);
        String sortedKey = ""; //Возвращаем в форму String
        for(int i = 0; i < arrkey.length; i++){
            sortedKey += arrkey[i];
        }

        int[] sortNumKey = new int[key.length()]; //Формируем массив номеров key
        for(int i = 0; i < sortNumKey.length; i++){
            sortNumKey[i] = sortedKey.indexOf(key.charAt(i));
            sortedKey = sortedKey.replaceFirst("" + key.charAt(i), "|");
        }

        int temp;
        for(int i = sortNumKey.length - 1; i >= 1; i--){
            for(int j = 0; j < i; j++){
                if(sortNumKey[j] > sortNumKey[j+1]){
                    temp = sortNumKey[j];
                    sortNumKey[j] = sortNumKey[j + 1];
                    sortNumKey[j + 1] = temp;
                    swapColumns(arr, j, j + 1);
                }
            }
        }

        String res = "";
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                res += arr[i][j];
            }
        }
        return res;
    }
    public static int[] twoProduct(int[] arr, int n){ // Создайте метод, который принимает массив arr и число n и возвращает массив из двух целых чисел из arr, произведение которых равно числу n следующего вида:
        int max = -1;
        int low = -1;
        int largestGap = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++){ //Проверяем массив
            if (n % arr[i] == 0){ //Если число делится на искомое
                for (int j = i + 1; j < arr.length; j++){
                    if (arr[i] * arr[j] == n) { //Если произведение правильное, то проверяем наименьшее ли расстояние
                        if (largestGap > j - i) {
                            low = i;
                            max = j;
                            largestGap = max - low;
                        }
                    }
                }
            }
        }
        if (max == -1 || low == -1) //В случае если ничего не нашли
            return new int[]{};
        int[] result = new int[]{arr[low],arr[max]};
        return result;
    }
    public static int isExact(int chislo, int n){ // рекурсия для факториала
        if(chislo % n == 0){
            if(chislo / n == 1){
                return n;
            }else{
                return isExact(chislo / n,n  + 1);
            }

        }else{
            return 0;
        }
    }
    public static String fractions(String fraction){ // получить дробь
        BigInteger intPart = new BigInteger(fraction.substring(0, fraction.indexOf('.'))); //Получаем целую часть
        String buff = "";
        int period = Integer.parseInt(fraction.substring(fraction.indexOf('(') + 1, fraction.indexOf(')'))); //Получаем периодическую часть
        buff = fraction.substring(fraction.indexOf('.') + 1, fraction.indexOf('('));  //Получаем дробную часть до периода
        int beforePeriod = Integer.parseInt(buff.equals("") ? "0" : buff);
        int reduce = Integer.parseInt(fraction.substring(fraction.indexOf('.') + 1, fraction.indexOf('(')) +
                fraction.substring(fraction.indexOf('(') + 1, fraction.indexOf(')'))); //Берем все цифрыы после запятой
        int numerator = reduce - beforePeriod;  //Числитель
        String denominator = "";  //Знаменатель

        for(int i = 0; i < ("" + period).length(); i++){ //Строим знаменатель
            denominator += "9";
        }
        for(int i = 0; beforePeriod != 0 && i < ("" + beforePeriod).length(); i++){
            denominator += "0";
        }

        BigInteger n = new BigInteger("" + numerator);
        BigInteger d = new BigInteger(denominator);
        BigInteger g;


        while(!(g = n.gcd(d)).equals(new BigInteger("1"))){  //Делим на наибольший общий делитель пока он не 1
            n = n.divide(g);
            d = d.divide(g);
        }

        if(!intPart.equals(new BigInteger("0"))){ //Учитываем целую часть
            n = intPart.multiply(d).add(n);
        }

        return "" + n.toString() + " / " + d.toString();

    }
    public static String pilish_string(String s){ // делить строки по цифрам числа пи
        if(s.equals("")){
            return "";
        }
        String pi = "314159265358979";
        int length;

        String res = "";

        for(int i = 0; pi.length() != 0 && i < s.length(); i += length){
            length = Integer.parseInt(pi.substring(0, 1)); //Получаем требуемую длину
            pi = pi.substring(1); //Убираем ее из pi
            if(i + length >= s.length()){ //Если требуемая длина больше исходной строки
                res += s.substring(i);  //Копируем остаток слова
                char ch = res.charAt(res.length() - 1);  //Дописываем последний символ
                for(int j = 0; j < i + length - s.length(); j++){
                    res += ch;
                }
                res += " ";
            }
            else {
                res += s.substring(i, i + length) + " "; //Получаем подстроку
            }
        }
        return res.substring(0, res.length() - 1);
    }
    public static String checkCons(int d, int n){ // доб для 8
        String res = "";
        for(int i = (int)Math.pow(2, n - 1); i > 0; i = i >> 1){ //Строим двоичное представление
            if((d & i) != 0){
                res += "1";
            }else{
                res += "0";
            }
        }
        if(!res.contains("11")){
            return res;
        }else{
            return null;
        }
    }
    public static String generateNonconsecutive(int n){  //Генерация строки двоичных чисел от 0 до 2^n - 1, которые не содержат "11"
        int last = (int)Math.pow(2, n) - 1;
        String res = "", buff = "";
        for(int d = 0; d <= last; d++){
            if((buff = checkCons(d, n)) != null){  //Если число подходит
                res += buff + " ";
            }
        }
        return res.substring(0, res.length() - 1);
    }

    public static String isValid(String s){ // Шерлок считает строку действительной, если все символы строки встречаются одинаковое количество раз.
        HashMap<Character, Integer> map = new HashMap<>();
        Integer n = new Integer(0);
        for(int i = 0; i < s.length(); i++){
            if((n = map.get(s.charAt(i))) != null){ //Если элемент есть
                map.put(s.charAt(i), n + 1);
            }else{
                map.put(s.charAt(i), 1); //Если нет, то кладем первый элемент
            }
        }
        Integer min = new Integer(s.length()), temp = new Integer(0);
        int diff_1 = 0, diff_more_1 = 0;
        for (HashMap.Entry e: map.entrySet()) {   //Найдем min
            temp = (Integer)(e.getValue());
            if(temp < min){
                min = temp;
            }
        }
        for (HashMap.Entry e: map.entrySet()) {   //Найдем кол-во значений, больших min на 1 и на более чем 1
            temp = (Integer)(e.getValue());
            if(temp > min){
                if(temp - min == 1){
                    diff_1++;
                }else if(temp - min > 1){
                    diff_more_1++;
                }
            }
        }
        if(diff_more_1 > 0){
            return "NO";
        }
        if(diff_1 > 1){
            return "NO";
        }
        return "YES";
    }
    public static ArrayList<ArrayList<Integer>> sumsUp(int[] arr){ //Создайте функцию, которая получает каждую пару чисел из массива, который суммирует до восьми, и возвращает его как массив пар
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++){
            for (int j = i + 1; j < arr.length; j++){

                if (arr[i] + arr[j] == 8) {//проверяем сложение
                    ArrayList<Integer> add = new ArrayList<>();
                    add.add(arr[i]);
                    add.add(arr[j]);
                    Collections.sort(add); //Сортируем добавляем
                    result.add(add);
                }
            }
        }
        return result;
    }
}
