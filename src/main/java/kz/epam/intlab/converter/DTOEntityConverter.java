package kz.epam.intlab.converter;

import kz.epam.intlab.dto.DTOModel;
import kz.epam.intlab.entity.EntityParent;

public interface DTOEntityConverter<T extends EntityParent> {
    T convertDTOToEntity(DTOModel model);
    DTOModel convertEntityToDTO(T entity);
}
