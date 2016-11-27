require_relative '../game/config.rb'

class MineralEstate < Place
  attr_reader :points
  def initialize(points)
    @points = points
  end

  def visit_by(player)
    player.gain_points(@points)
    :end_turn
  end

end
