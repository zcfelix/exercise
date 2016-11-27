require_relative '../game/config.rb'

class GiftRoom < Place
  def visit_by(player)
    :wait_for_choose_gift
  end

  def act_to(player, response)
    if (response == :choose_money)
      player.gain(GiftConf::BONUS_MONEY)
    elsif (response == :choose_points)
      player.gain_points(GiftConf::BONUS_POINTS)
    elsif (response == :choose_mascot)
      # +1 是为了抵消本回合自动-1的尴尬...暂时没想到更好的实现
      player.no_punish_turns = GiftConf::TURN_WITH_MASCOT + 1
    end
    :end_turn
  end
end
