require_relative '../game/config.rb'

class Prison < Place
  def visit_by(player)
    player.paused_turns = PrisonConf::PAUSED_TURNS
    :wait_for_turn
  end

end
