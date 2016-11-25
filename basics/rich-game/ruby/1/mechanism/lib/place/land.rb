class Land < Place
  attr_accessor :owner
  attr_reader  :level, :price
  def initialize(price)
    @price = price
    @level = 0
    @owner = nil
  end

  def visit_by(player)
    if (@owner.nil?)
      :wait_for_buy
    elsif (@owner == player)
      :wait_for_upgrade
    else
      player.pay(road_toll)
      owner.gain(road_toll)
      if (player.balance >= 0)
        :end_turn
      else
        :game_over
      end
    end
  end

  def upgrade
    @level += 1 unless owner.nil?
  end

  def act_to(player, response)
    if (player.status == :wait_for_buy)
      if (response == :yes)
        player.buy_land(self)
        :end_turn
      elsif (response == :no)
        :end_turn
      end
    elsif (player.status == :wait_for_upgrade)
      if (response == :yes)
        player.upgrade(self)
        :end_turn
      elsif (response == :no)
        :end_turn
      end
    end

  end

  def road_toll
    return 0 if @owner.nil?
    2 ** level * @price / 2
  end

end
