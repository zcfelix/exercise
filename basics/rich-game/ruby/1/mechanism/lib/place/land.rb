class Land < Place
  attr_accessor :owner
  attr_reader  :level, :price
  def initialize(price)
    @price = price
    @level = 0
  end

  def visit_by(player)
    if (owner.nil?)
      :wait_for_buy
    elsif (owner == player)
      :wait_for_upgrade
    else
      :end_turn
    end
  end

  def upgrade
    @level += 1 unless owner.nil?
  end

end
