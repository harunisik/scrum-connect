package com.harunisik.userdata.model.event;

import com.harunisik.userdata.model.Userdata;
import com.harunisik.userdata.service.SequenceGeneratorService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class UserdataModelListener extends AbstractMongoEventListener<Userdata> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public UserdataModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Userdata> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Userdata.SEQUENCE_NAME));
            event.getSource().setDateCreated(new Date());
        }
    }
}