package org.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


import java.io.Serializable;
import java.util.Date;
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutObject implements Serializable {



    private static final long serialVersionUID = -1734031177718628026L;
    private Object out;

    private int code;

    private String msg;

    private Date date;
}
