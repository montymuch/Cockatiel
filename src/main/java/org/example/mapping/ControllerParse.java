package org.example.mapping;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

public interface ControllerParse {
   public Object readProperties(HashSet controllers) throws IOException;
   public Object readXML(HashSet controllers);
   public Object readYML(HashSet controllers);}

