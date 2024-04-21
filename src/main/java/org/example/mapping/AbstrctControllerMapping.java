package org.example.mapping;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

public abstract class AbstrctControllerMapping {

    public  abstract  Object readProperties(HashSet controllers) throws IOException;


    public abstract  Object readXML(HashSet controllers);


    public abstract  Object readYML(HashSet controllers) ;
}
