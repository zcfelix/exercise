require_relative '../game/config.rb'

class Robot < Tool
  def initialize
    super(ToolConf::ROBOT_POINTS)
  end
end
