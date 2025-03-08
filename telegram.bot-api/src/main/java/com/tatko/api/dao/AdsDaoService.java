package com.tatko.api.dao;

import com.tatko.api.apis.models.FilterAdApiRequest;
import com.tatko.api.entities.AdJpaEntity;
import com.tatko.api.repositories.AdsRepository;
import com.tatko.api.services.PredicatesHolderDateTimeClassService;
import com.tatko.api.services.PredicatesHolderLongClassService;
import com.tatko.api.services.PredicatesHolderStringClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdsDaoService {

    /**
     * Autowired by Spring DaoService class instance.
     */
    @Autowired
    private DaoService daoService;

    /**
     * Autowired by Spring AdsRepository class instance.
     */
    @Autowired
    private AdsRepository adsRepository;

    /**
     * Autowired by Spring PredicatesHolderLongClassService class instance.
     */
    @Autowired
    private PredicatesHolderLongClassService predicatesHolderLongClassService;
    /**
     * Autowired by Spring PredicatesHolderStringClassService class instance.
     */
    @Autowired
    private
    PredicatesHolderStringClassService predicatesHolderStringClassService;
    /**
     * Autowired by Spring PredicatesHolderDateTimeClassService class instance.
     */
    @Autowired
    private
    PredicatesHolderDateTimeClassService predicatesHolderDateTimeClassService;

    /**
     * Find AdJpaEntity in DB based on Ad ID.
     *
     * @param adId Ad ID.
     * @return AdJpaEntity class instance.
     */
    public Optional<AdJpaEntity> findById(final Long adId) {

        Optional<AdJpaEntity> adJpaEntityOptional
                = adsRepository.findById(adId);

        return adJpaEntityOptional;
    }

    /**
     * Save AdJpaEntity in DB.
     *
     * @param adJpaEntity AdJpaEntity class instance.
     * @return Saved AdJpaEntity class instance.
     */
    public AdJpaEntity save(final AdJpaEntity adJpaEntity) {

        AdJpaEntity adJpaEntitySaved = adsRepository.save(adJpaEntity);

        return adJpaEntitySaved;

    }

    /**
     * Delete Ad by ID.
     *
     * @param adId Ad ID.
     */
    public void deleteById(final Long adId) {

        adsRepository.deleteById(adId);

    }

    /**
     * Filter Ad based on input data.
     *
     * @param filterAdApiRequest FilterAdApiRequest class instance.
     * @param pageable           Pageable class instance.
     * @return List of AdJpaEntity class instances.
     */
    public List<AdJpaEntity> adsFilter(
            final FilterAdApiRequest filterAdApiRequest,
            final Pageable pageable) {

        PredicateCreator predicateCreator = (criteriaBuilder, root)
                -> buildPredicate(criteriaBuilder, root, filterAdApiRequest);

        List<AdJpaEntity> adJpaEntityList = daoService.entityFilter(
                predicateCreator,
                pageable,
                AdJpaEntity.class
        );

        return adJpaEntityList;

    }

    /**
     * @param criteriaBuilder CriteriaBuilder class instance.
     * @param predicates      List of predicates.
     * @return Predicate class instance.
     */
    Predicate mergePredicates(
            final CriteriaBuilder criteriaBuilder,
            final List<Optional<Predicate>> predicates) {

        Predicate predicate = criteriaBuilder.and(
                predicates.stream()
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .toList()
                        .toArray(new Predicate[0]));
        return predicate;

    }

    /**
     * Build Predicate by input data.
     *
     * @param criteriaBuilder    CriteriaBuilder class instance.
     * @param root               From  class instance.
     * @param filterAdApiRequest FilterAdApiRequest class instance.
     * @return Predicate class instance.
     */
    public Predicate buildPredicate(
            final CriteriaBuilder criteriaBuilder,
            final From root, final FilterAdApiRequest filterAdApiRequest) {

        List<Optional<Predicate>> predicates = new ArrayList<>();

        // --------------------------------------------------------------------
        predicates.add(predicatesHolderLongClassService.buildPredicate(
                criteriaBuilder, root, "id", filterAdApiRequest.getId()));
        predicates.add(predicatesHolderStringClassService.buildPredicate(
                criteriaBuilder, root, "ad", filterAdApiRequest.getAd()));
        predicates.add(predicatesHolderDateTimeClassService.buildPredicate(
                criteriaBuilder, root, "deliveredTime",
                filterAdApiRequest.getDeliveredTime()));
        predicates.add(predicatesHolderDateTimeClassService.buildPredicate(
                criteriaBuilder, root, "creatingTime",
                filterAdApiRequest.getCreatingTime()));
        // --------------------------------------------------------------------

        Predicate predicate = mergePredicates(criteriaBuilder, predicates);

        return predicate;

    }

}
