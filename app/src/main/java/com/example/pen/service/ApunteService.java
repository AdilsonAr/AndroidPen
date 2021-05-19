package com.example.pen.service;

import com.example.pen.model.ApunteFace;
import com.example.pen.model.ApunteKeyValue;
import com.example.pen.model.ApunteSimple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApunteService {
    public List<ApunteFace> getFaces(List<ApunteKeyValue> keyValues, List<ApunteSimple> simples) throws Exception{
        List<ApunteFace> faces=new ArrayList<>();
        for(ApunteKeyValue c:keyValues){
            faces.add(new ApunteFace(c.getId(),DateFormats.DATE_TIME.getFormat().parse(c.getFecha()), c.getNombre(),c.getTipo()));
        }

        for(ApunteSimple c:simples){
            faces.add(new ApunteFace(c.getId(),DateFormats.DATE_TIME.getFormat().parse(c.getFecha()),c.getNombre(),c.getTipo()));
        }
        Collections.sort(faces,(x,y)->{return -(x.getFecha().compareTo(y.getFecha()));});
        return  faces;
    }
}
