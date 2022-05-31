package lotto.domain

object LottoStore {
    const val LOTTO_TICKET_PRICE = 1000

    fun buyLottoTicket(purchaseAmount: Int): LottoTickets {
        require(purchaseAmount > LOTTO_TICKET_PRICE) {
            "금액이 부족합니다."
        }

        val numberOfTicket = purchaseAmount / LOTTO_TICKET_PRICE
        return LottoTicketMachine.generate(numberOfTicket)
    }
}
