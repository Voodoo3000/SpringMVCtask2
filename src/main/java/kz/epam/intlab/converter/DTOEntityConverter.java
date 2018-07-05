package kz.epam.intlab.converter;

import kz.epam.intlab.dto.ParentDTO;
import kz.epam.intlab.entity.EntityParent;

public interface DTOEntityConverter<T extends EntityParent, K extends ParentDTO> {
    T convertDTOToEntity(K model);
    K convertEntityToDTO(T entity);
}
