package com.tcs.cuenta_service.service.implement;

import com.tcs.cuenta_service.exceptions.CustomExceptions;
import com.tcs.cuenta_service.model.dto.CuentaSimpleDTO;
import com.tcs.cuenta_service.model.dto.mapper.ICuentaSimpleDTOMapper;
import com.tcs.cuenta_service.model.entity.Cuenta;
import com.tcs.cuenta_service.repository.CuentaRepository;
import com.tcs.cuenta_service.service.ICuentaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CuentaServiceImpl implements ICuentaService {
    private final CuentaRepository cuentaRepository;
    private final ICuentaSimpleDTOMapper cuentaSimpleDTOMapper;

    public CuentaServiceImpl(
            CuentaRepository cuentaRepository,
            ICuentaSimpleDTOMapper cuentaSimpleDTOMapper) {
        this.cuentaRepository = cuentaRepository;
        this.cuentaSimpleDTOMapper = cuentaSimpleDTOMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CuentaSimpleDTO> listarCuentas() {
        List<Cuenta> cuentas = cuentaRepository.findAll();

        return cuentaSimpleDTOMapper.toDtoList(cuentas);
    }

    @Override
    @Transactional
    public CuentaSimpleDTO crearCuenta(CuentaSimpleDTO cuentaSimpleDTO) {
//        if (!clienteFeignClient.existsClienteById(cuentaSimpleDTO.getClienteId())) {
//            throw new CustomExceptions.BadRequestException("El cliente con ID " + cuentaSimpleDTO.getClienteId() + " no existe.");
//        }

        Cuenta cuenta = cuentaSimpleDTOMapper.toEntity(cuentaSimpleDTO);

        cuenta = cuentaRepository.save(cuenta);

        return cuentaSimpleDTOMapper.toDto(cuenta);
    }

    @Override
    @Transactional
    public CuentaSimpleDTO actualizarCuenta(CuentaSimpleDTO cuentaSimpleDTO) {
        Long id = cuentaSimpleDTO.getId();

        if (!cuentaRepository.existsById(id)) {
            throw new CustomExceptions.NotFoundException("La Cuenta con ID: " + id + " no se ha encontrado");
        }

        Cuenta cuentaActualizada = cuentaSimpleDTOMapper.toEntity(cuentaSimpleDTO);
        cuentaActualizada.setId(cuentaSimpleDTO.getId());

        return cuentaSimpleDTOMapper.toDto(cuentaRepository.save(cuentaActualizada));
    }

    @Override
    @Transactional
    public void eliminarCuenta(String cuentaId) {
        Cuenta cuenta = cuentaRepository.findById(Long.valueOf(cuentaId))
                .orElseThrow(() -> new CustomExceptions.NotFoundException("La Cuenta con ID: " + cuentaId + " no se ha encontrado"));

        cuentaRepository.delete(cuenta);
    }

    @Override
    @Transactional(readOnly = true)
    public CuentaSimpleDTO obtenerCuentaPorId(String cuentaId) {
        Cuenta cliente = cuentaRepository.findById(Long.valueOf(cuentaId))
                .orElseThrow(() -> new CustomExceptions.NotFoundException("La Cuenta con ID: " + cuentaId + " no se ha encontrado"));

        return cuentaSimpleDTOMapper.toDto(cliente);
    }
}