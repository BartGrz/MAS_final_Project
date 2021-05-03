package com.pl.bg.javamasproject.demo.models;

import com.pl.bg.javamasproject.demo.tools.Looper;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.*;


@Entity
@Table(name = "patients")
@Component
@NoArgsConstructor
@ToString(exclude = "client")
@Builder
@AllArgsConstructor
public class Patient extends EntityTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;
    @Getter
    @Column(name = "patient_name")
    private  String patient_name;
    @Getter
    @Column(name = "id_card")
    private int id_card;
    @Getter
    @Column(name = "id_client")
    private int id_client;
    @Getter
    @ManyToOne
    @JoinColumn(name = "id_client", insertable = false ,updatable = false)
    private Client client;


    public static List<String> allFields() {

            List<String> fieldsList = new ArrayList<>();
            Field[] tab =new Patient().getClass().getDeclaredFields();
            Looper.forLoop(0,tab.length, i -> fieldsList.add(tab[i].getName()));

            return fieldsList;
    }


    public static List<String> getListOfTableFields() {

        List<String> fieldsList = new ArrayList<>();
        Field[] tab =new Patient().getClass().getDeclaredFields();

        Looper.forLoop(1,tab.length,i -> {
            for (int j = 0; j < listColumns.size(); j++) {
                if (!tab[i].getType().getSimpleName().equals(listColumns.get(j))) {
                } else {
                    fieldsList.add(tab[i].getName());
                }
            }
        });
        return fieldsList;
    }

    @PostConstruct
    public void validate() {

        List<String> list = allFields();
        boolean[] tab = new boolean[list.size()];

        int index = 0;
        for (Iterator it = fieldsEnum().iterator(); it.hasNext(); ) {
            String enumField = it.next().toString().toLowerCase();
            if (list.get(index).equals(enumField)) {

                tab[index] = true;
            } else {
                tab[index] = false;
            }
            index += 1;
        }
        for (int i = 0; i<tab.length;i++) {

            if (tab[i]) {

            }else {
                logger.error("NOT ALL FIELDS FROM CLASS ARE ADDED TO ENUM CLASS, fields missing : " + list.get(i));
                break;
            }
        }
        logger.info("VALIDATION OK");
    }

    @Override
    public EnumSet fieldsEnum() {

        return EnumSet.allOf(fieldsNames.class);
    }
    public enum fieldsNames{
      ID,PATIENT_NAME,ID_CARD,ID_CLIENT,CLIENT;

    }


}


