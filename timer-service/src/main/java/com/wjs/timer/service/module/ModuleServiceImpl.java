package com.wjs.timer.service.module;

import com.wjs.timer.api.dto.ModuleDTO;
import com.wjs.timer.domain.module.Module;
import com.wjs.timer.domain.module.ModuleRpt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleRpt moduleRpt;

    @Override
    @Transactional
    public List<ModuleDTO> queryAll() {
        List<Module> modules = moduleRpt.queryList();
        List<ModuleDTO> moduleDTOs = new ArrayList<>();
        modules.forEach(module -> {
            moduleDTOs.add(module.makeDTO());
        });
        return moduleDTOs;
    }
}
