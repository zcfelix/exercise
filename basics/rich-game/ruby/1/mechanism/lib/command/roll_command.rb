require_relative 'command.rb'

class RollCommand < Command
  def initialize(map, step)
    @map = map
    @step = step
  end

  def execute(player)
    target = @map.move(player.current_place, @step)
    player.move_to(target)
    target.visit_by(player)
  end

  def execute_with(player, response)
    target = player.current_place
    target.act_to(player, response)
  end
end
