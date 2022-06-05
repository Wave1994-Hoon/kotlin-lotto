package lotto.domain

data class LottoTickets(
    private val lottoTickets: List<LottoTicket>
) : List<LottoTicket> by lottoTickets {
    init {
        require(lottoTickets.isNotEmpty()) {
            "로또 티켓 개수가 1개 이상이여야 합니다."
        }
    }

    fun getLottoTickets(): List<LottoTicket> {
        return lottoTickets
    }

    fun getMatchCount(lottoRank: LottoRank, winningNumbers: LottoWinningNumber): Int {
        return lottoTickets.count() { lottoTicket -> lottoTicket.isMatch(lottoRank, winningNumbers) }
    }
}
