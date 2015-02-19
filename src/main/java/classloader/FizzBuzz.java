package classloader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.addAll;

public class FizzBuzz
{
    public static void main(String[] args)
        throws Exception
    {
        final ClassLoader loader= new ClassLoader(){
            private final Map<String, Class<?>> defined= new HashMap<>();

            @Override
            protected Class<?> findClass(String name)
                throws ClassNotFoundException
            {
                final int i= Integer.parseInt(name);
                final String cname;
                if(i % 15 == 0)
                {
                    cname= "FizzBuzz";
                }
                else if(i % 3 == 0)
                {
                    cname= "Fizz";
                }
                else if(i % 5 == 0)
                {
                    cname= "Buzz";
                }
                else
                {
                    cname= "" + i;
                }

                if(this.defined.containsKey(cname))
                {
                    return this.defined.get(cname);
                }

                final byte[] bytes= this.makeClass(cname);
                final Class<?> clazz= this.defineClass(cname, bytes, 0, bytes.length);

                this.defined.put(cname, clazz);

                return clazz;
            }

            private byte[] makeClass(CharSequence name)
            {
                final List<Integer> bytes= new LinkedList<>();

                // magic
                addAll(bytes, 0xca, 0xfe, 0xba, 0xbe);
                // minor version
                addAll(bytes, 0x00, 0x00);
                // major version
                addAll(bytes, 0x00, 0x34);
                // constant pool count
                addAll(bytes, 0x00, 0x05);
                // constant pool
                // CONSTANT_Class_info
                addAll(bytes, 0x07);
                addAll(bytes, 0x00, 0x02);
                // CONSTANT_Utf8_info
                addAll(bytes, 0x01);
                addAll(bytes, 0x00, name.length());
                name.chars().forEach(bytes::add);
                // CONSTANT_Class_info
                addAll(bytes, 0x07);
                addAll(bytes, 0x00, 0x04);
                // CONSTANT_Utf8_info
                addAll(bytes, 0x01);
                {
                    final String superclazz= "java/lang/Object";
                    addAll(bytes, 0x00, superclazz.length());
                    superclazz.chars().forEach(bytes::add);
                }
                // access flags
                addAll(bytes, 0x00, 0x21);
                // this class
                addAll(bytes, 0x00, 0x01);
                // super class
                addAll(bytes, 0x00, 0x03);
                // interfaces count
                addAll(bytes, 0x00, 0x00);
                // fields count
                addAll(bytes, 0x00, 0x00);
                // methods count
                addAll(bytes, 0x00, 0x00);
                // attributes count
                addAll(bytes, 0x00, 0x00);

                final byte[] transformed= new byte[bytes.size()];
                for(int i= 0; i < bytes.size(); ++i)
                {
                    transformed[i]= bytes.get(i).byteValue();
                }
                return transformed;
            }
        };

        for(int i= 1; i <= 100; ++i)
        {
            System.out.println(loader.loadClass("" + i).getSimpleName());
        }
    }
}
