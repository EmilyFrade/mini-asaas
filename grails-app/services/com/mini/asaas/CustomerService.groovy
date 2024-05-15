package com.mini.asaas

import core.dtos.AddressDTO
import core.dtos.CustomerDTO
import core.exceptions.EntityNoDataChangedException
import core.exceptions.EntityNotFoundException
import core.exceptions.EntityWithSamePropertyAlreadyExistsException
import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

/**
 * Serviço responsável por gerenciar as operações de {@code Customer}.
 */
@GrailsCompileStatic
@Transactional
class CustomerService {

    /**
     * Cria um novo {@code Customer} a partir dos dados pessoais e de endereço informados.
     *
     * @param customerDTO Dados pessoais do {@code Customer}.
     * @param addressDTO Dados de endereço do {@code Customer}.
     * @return {@code Customer} criado.
     *
     * @throws EntityWithSamePropertyAlreadyExistsException Se já existir um {@code Customer} com o mesmo e-mail ou CPF/CNPJ.
     * @throws ValidationException Se ocorrer um erro ao validar os dados pessoais.
     *
     * @see CustomerDTO
     * @see AddressDTO
     * @see Customer
     * @see Customer#address
     */
    public Customer save(CustomerDTO customerDTO, AddressDTO addressDTO) {

        if (Customer.findByCpfCnpj(customerDTO.cpfCnpj)) {
            throw new EntityWithSamePropertyAlreadyExistsException("Já existe um cliente cadastrado com o CPF/CNPJ informado")
        }

        if (Customer.findByEmail(customerDTO.email)) {
            throw new EntityWithSamePropertyAlreadyExistsException("Já existe um cliente cadastrado com o e-mail informado")
        }

        Customer customer = customerDTO.toCustomer()
        customer.address = addressDTO.toAddress()

        if (!customer.validate()) {
            throw new ValidationException("Ocorreu um erro ao validar os dados", customer.errors)
        }

        customer.save(failOnError: true)

        return customer
    }

    /**
     * Atualiza os dados pessoais de um {@code Customer}.
     *
     * @param id Identificador do {@code Customer}.
     * @param customerDTO Novos dados pessoais.
     * @return {@code Customer} atualizado.
     *
     * @throws EntityNotFoundException Se o {@code Customer} não for encontrado.
     * @throws EntityNoDataChangedException Se os dados pessoais não forem enviados ou não forem diferentes dos dados atuais.
     * @throws EntityWithSamePropertyAlreadyExistsException Se já existir um {@code Customer} com o mesmo e-mail ou CPF/CNPJ.
     * @throws ValidationException Se ocorrer um erro ao validar os dados pessoais.
     *
     * @see CustomerDTO
     * @see Customer
     */
    public Customer update(Long id, CustomerDTO customerDTO) {
        Customer customer = Customer.get(id)

        if (!customer) {
            throw new EntityNotFoundException("Cliente não encontrado")
        }

        if (!customerDTO) {
            throw new EntityNoDataChangedException()
        }

        if (!this.isUpdatedData(customerDTO, customer)) {
            throw new EntityNoDataChangedException()
        }

        Boolean isNewEmail = customer.email != customerDTO.email
        if (isNewEmail && Customer.findByEmail(customerDTO.email)) {
            throw new EntityWithSamePropertyAlreadyExistsException("Já existe um cliente cadastrado com o e-mail informado")
        }

        Boolean isNewCpfCnpj = customer.cpfCnpj != customerDTO.cpfCnpj
        if (isNewCpfCnpj && Customer.findByCpfCnpj(customerDTO.cpfCnpj)) {
            throw new EntityWithSamePropertyAlreadyExistsException("Já existe um cliente cadastrado com o CPF/CNPJ informado")
        }

        customer = customerDTO.updateCustomer(customer)
        customer.markDirty()

        if (!customer.validate()) {
            throw new ValidationException("Ocorreu um erro ao validar os dados", customer.errors)
        }

        customer.save(failOnError: true)

        return customer
    }

    /**
     * Atualiza o endereço de um {@code Customer}.
     *
     * @param id Identificador do {@code Customer}.
     * @param addressDTO Novos dados de endereço.
     * @return {@code Customer} atualizado.
     *
     * @throws EntityNotFoundException Se o {@code Customer} não for encontrado.
     * @throws EntityNoDataChangedException Se os dados de endereço não forem enviados ou não forem diferentes dos dados atuais.
     * @throws EntityWithSamePropertyAlreadyExistsException Se já existir um {@code Customer} com o mesmo e-mail ou CPF/CNPJ.
     * @throws ValidationException Se ocorrer um erro ao validar os dados de endereço.
     *
     * @see AddressDTO
     * @see Customer
     * @see Customer#address
     */
    public Customer updateAddress(Long id, AddressDTO addressDTO) {
        Customer customer = Customer.get(id)

        if (!customer) {
            throw new EntityNotFoundException("Cliente não encontrado")
        }

        if (!addressDTO) {
            throw new EntityNoDataChangedException()
        }

        if (!this.isUpdatedData(addressDTO, customer.address)) {
            throw new EntityNoDataChangedException()
        }

        customer.address = addressDTO.toAddress()
        customer.markDirty("address")

        if (!customer.validate()) {
            throw new ValidationException("Ocorreu um erro ao validar os dados de endereço", customer.errors)
        }

        return customer
    }

    /**
     * Verifica se os dados enviados para atualização são diferentes dos dados atuais.
     *
     * @param dto Dados enviados para atualização.
     * @param entity Entidade a ser atualizada.
     * @return {@code true} se os dados foram atualizados, {@code false} caso contrário.
     */
    private boolean isUpdatedData(Object dto, Object entity) {
        boolean isUpdated = false
        for (String propertyName : dto.properties.keySet()) {
            if (propertyName == "class") continue
            if (entity[propertyName] != dto[propertyName]) {
                isUpdated = true
                break
            }
        }
        return isUpdated
    }

}
