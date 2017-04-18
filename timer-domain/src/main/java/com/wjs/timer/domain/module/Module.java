package com.wjs.timer.domain.module;

import com.wjs.common.base.base.BaseEntity;
import com.wjs.timer.api.dto.ModuleDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.wjs.common.base.util.BeanPropertiesUtil.copyProperties;

@Setter
@Getter
@ToString
public class Module extends BaseEntity {

    public Module() {
    }

    public ModuleDTO makeDTO() {
        return copyProperties(this, ModuleDTO.class);
    }
}
