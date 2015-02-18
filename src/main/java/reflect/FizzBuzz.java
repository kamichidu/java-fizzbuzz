package reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class FizzBuzz
{
    public static interface Dummy
    {
        CharSequence _1();  CharSequence _2();  CharSequence _3();  CharSequence _4();  CharSequence _5();  CharSequence _6();  CharSequence _7();  CharSequence _8();  CharSequence _9();  CharSequence _10();
        CharSequence _11(); CharSequence _12(); CharSequence _13(); CharSequence _14(); CharSequence _15(); CharSequence _16(); CharSequence _17(); CharSequence _18(); CharSequence _19(); CharSequence _20();
        CharSequence _21(); CharSequence _22(); CharSequence _23(); CharSequence _24(); CharSequence _25(); CharSequence _26(); CharSequence _27(); CharSequence _28(); CharSequence _29(); CharSequence _30();
        CharSequence _31(); CharSequence _32(); CharSequence _33(); CharSequence _34(); CharSequence _35(); CharSequence _36(); CharSequence _37(); CharSequence _38(); CharSequence _39(); CharSequence _40();
        CharSequence _41(); CharSequence _42(); CharSequence _43(); CharSequence _44(); CharSequence _45(); CharSequence _46(); CharSequence _47(); CharSequence _48(); CharSequence _49(); CharSequence _50();
        CharSequence _51(); CharSequence _52(); CharSequence _53(); CharSequence _54(); CharSequence _55(); CharSequence _56(); CharSequence _57(); CharSequence _58(); CharSequence _59(); CharSequence _60();
        CharSequence _61(); CharSequence _62(); CharSequence _63(); CharSequence _64(); CharSequence _65(); CharSequence _66(); CharSequence _67(); CharSequence _68(); CharSequence _69(); CharSequence _70();
        CharSequence _71(); CharSequence _72(); CharSequence _73(); CharSequence _74(); CharSequence _75(); CharSequence _76(); CharSequence _77(); CharSequence _78(); CharSequence _79(); CharSequence _80();
        CharSequence _81(); CharSequence _82(); CharSequence _83(); CharSequence _84(); CharSequence _85(); CharSequence _86(); CharSequence _87(); CharSequence _88(); CharSequence _89(); CharSequence _90();
        CharSequence _91(); CharSequence _92(); CharSequence _93(); CharSequence _94(); CharSequence _95(); CharSequence _96(); CharSequence _97(); CharSequence _98(); CharSequence _99(); CharSequence _100();
    }

    public static class FizzBuzzHandler
        implements InvocationHandler
    {
        @Override
        public CharSequence invoke(Object proxy, Method method, Object[] args)
        {
            final int n= Integer.parseInt(method.getName().replaceFirst("^_", ""));

            if(n % 15 == 0)
            {
                return "FizzBuzz";
            }
            else if(n % 3 == 0)
            {
                return "Fizz";
            }
            else if(n % 5 == 0)
            {
                return "Buzz";
            }
            else
            {
                return "" + n;
            }
        }
    }

    public static void main(String[] args)
        throws Exception
    {
        final Dummy obj= (Dummy)Proxy.newProxyInstance(
            FizzBuzz.class.getClassLoader(),
            new Class<?>[]{Dummy.class},
            new FizzBuzzHandler()
        );

        for(final Method method : Dummy.class.getMethods())
        {
            System.out.println(method.invoke(obj));
        }
    }
}
