package lotto.domain

import lotto.service.LottoNumberPackagesGenerator
import java.math.BigDecimal
import java.math.RoundingMode

data class LottoTicket(private val _lottoPackages: List<LottoNumberPackage>) {
    val lottoPackages: List<LottoNumberPackage>
        get() {
            return _lottoPackages.toList()
        }

    fun buildResult(winningInfo: WinningInfo, purchaseAmount: LottoPurchaseAmount): LottoResult {
        return LottoResult(buildResultStatistics(winningInfo), buildTotalProfitRate(winningInfo, purchaseAmount))
    }

    private fun buildResultStatistics(winningInfo: WinningInfo): Map<LottoResultRank, Int> {
        return lottoPackages
            .groupingBy { LottoResultRank.getRank(it.getMatchedCount(winningInfo.winningNumberPackage), it.matchedBonusNumber(winningInfo.bonusNumber)) }
            .eachCount()
    }

    private fun buildTotalProfitRate(winningInfo: WinningInfo, purchaseAmount: LottoPurchaseAmount): BigDecimal {
        return lottoPackages
            .sumOf { it.getPrizeMoney(winningInfo) }
            .toBigDecimal()
            .setScale(2, RoundingMode.HALF_UP)
            .div(purchaseAmount.value!!.toBigDecimal())
    }

    companion object {
        fun from(
            manualNumbers: List<LottoNumberPackage>,
            automaticPurchaseCount: LottoPurchaseCount,
            packagesGenerator: LottoNumberPackagesGenerator
        ): LottoTicket {
            return LottoTicket(manualNumbers.plus(packagesGenerator.generate(automaticPurchaseCount)))
        }
    }
}