package feature.promotions.com.example.ffp.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "t_promotions")
class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotions_seq")
    @SequenceGenerator(name = "promotions_seq", sequenceName = "t_promotions_seq", allocationSize = 50)
    var id: Long? = null

    var code: String = ""

    var discountPercentage: BigDecimal = BigDecimal.ZERO
}
