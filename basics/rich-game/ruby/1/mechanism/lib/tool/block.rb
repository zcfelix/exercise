require_relative '../game/config.rb'
class Block < Tool
  def initialize
    super(ToolConf::BLOCK_POINTS)
  end
end
