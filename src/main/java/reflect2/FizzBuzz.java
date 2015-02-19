package reflect2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class FizzBuzz
{
    public static interface Dummy
    {
        default CharSequence trans(int i)
        {
            return "" + i;
        }
    }

    public static void main(String[] args)
        throws Exception
    {
        new Dummy(){{
            new Dummy(){
                @Override
                public CharSequence trans(int i)
                {
                    return "FizzBuzz";
                }
            };
            new Dummy(){
                @Override
                public CharSequence trans(int i)
                {
                    return "Buzz";
                }
            };
            new Dummy(){
                @Override
                public CharSequence trans(int i)
                {
                    return "Buzz";
                }
            };
        }};
        new Dummy(){{
            new Dummy(){
                @Override
                public CharSequence trans(int i)
                {
                    return "Fizz";
                }
            };
            new Dummy(){};
            new Dummy(){};
        }};
        new Dummy(){{
            new Dummy(){
                @Override
                public CharSequence trans(int i)
                {
                    return "Fizz";
                }
            };
            new Dummy(){};
            new Dummy(){};
        }};
        new Dummy(){{
            new Dummy(){
                @Override
                public CharSequence trans(int i)
                {
                    return "Fizz";
                }
            };
            new Dummy(){};
            new Dummy(){};
        }};
        new Dummy(){{
            new Dummy(){
                @Override
                public CharSequence trans(int i)
                {
                    return "Fizz";
                }
            };
            new Dummy(){};
            new Dummy(){};
        }};

        for(int i= 1; i <= 100; ++i)
        {
            final Class<?> declaringClazz= Class.forName(FizzBuzz.class.getName() + "$" + (i % 5 + 1));
            final Class<?> clazz= Class.forName(FizzBuzz.class.getName() + "$" + (i % 5 + 1) + "$" + (i % 3 + 1));

            final Constructor<?> ctor= clazz.getDeclaredConstructor(declaringClazz);
            final Method trans= clazz.getMethod("trans", int.class);

            System.out.println(trans.invoke(ctor.newInstance(declaringClazz.newInstance()), i));
        }
    }
}
