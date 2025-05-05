package com.erp.school.formcrud;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    public Form createForm(Form form) {
        return formRepository.save(form);
    }

    public Form getFormById(Long id) {
        return formRepository.findById(id).orElse(null);
    }

    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    public Form updateForm(Long id, Form form) {
        Form existingForm = formRepository.findById(id).orElse(null);
        if (existingForm != null) {
            existingForm.setFormId(form.getFormId());
            existingForm.setFields(form.getFields());
            return formRepository.save(existingForm);
        }
        return null;
    }

    public void deleteForm(Long id) {
        formRepository.deleteById(id);
    }
}