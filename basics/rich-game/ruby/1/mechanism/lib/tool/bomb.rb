require_relative '../game/config.rb'

class Bomb < Tool
  def initialize
    super(ToolConf::BOMB_POINTS)
  end
end
